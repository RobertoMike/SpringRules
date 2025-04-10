package io.github.robertomike.jakidate.constraints.passwords

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.sha1
import io.github.robertomike.jakidate.validations.passwords.NotCompromisedPassword
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * A constraint that checks if a password has not been compromised by checking it against the Pwned Passwords API.
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
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI("https://api.pwnedpasswords.com/range/${shortHash}"))
                .GET()
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            if (response.statusCode() != 200) {
                throw RulesException("Error, status code: ${response.statusCode()}")
            }

            response.body().split("\r\n")
                .asSequence()
                .map { shortHash + it.split(":")[0] }
                .none { it.equals(hash, ignoreCase = true) }
        } catch (e: Exception) {
            throw RulesException("There was an error communicating with the server", e)
        }
    }
}