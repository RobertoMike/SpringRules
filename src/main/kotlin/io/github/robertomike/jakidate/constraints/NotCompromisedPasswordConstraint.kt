package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.sha1
import io.github.robertomike.jakidate.validations.NotCompromisedPassword
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * A constraint that checks if a password has been compromised by checking it against the Pwned Passwords API.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class NotCompromisedPasswordConstraint : SimpleConstraint<NotCompromisedPassword, String>() {
    /**
     * Checks if the given password has been compromised.
     *
     * @param value the password to check
     * @return true if the password has not been compromised, false otherwise
     * @throws RulesException if there was an error communicating with the server
     */
    override fun isValid(value: String): Boolean {
        val hash = value.sha1()
        val shortHash = hash.substring(0, 5)

        return try {
            val url = URL("https://api.pwnedpasswords.com/range/${shortHash}")
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"

            val responseCode = con.getResponseCode()

            if (responseCode != 200) {
                throw RulesException("Error, status code: $responseCode")
            }

            getBody(con).split("\r\n")
                .asSequence()
                .map { shortHash + it.split(":")[0] }
                .none { it.equals(hash, ignoreCase = true) }
        } catch (e: Exception) {
            throw RulesException("There was an error communicating with the server", e)
        }
    }

    private fun getBody(con: HttpURLConnection): String {
        val input = BufferedReader(InputStreamReader(con.inputStream))
        var inputLine: String?
        val response = StringBuffer()
        while ((input.readLine().also { inputLine = it }) != null) {
            response.append("$inputLine\r\n")
        }
        input.close()
        return response.toString()
    }
}