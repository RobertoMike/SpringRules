package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.validations.ip.Ip

/**
 * This class represents a constraint for validating IP addresses.
 *
 * It provides a simple way to validate IP addresses using regular expressions.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class IpConstraint: SimpleConstraint<Ip, String>() {
    companion object {
        /**
         * The regular expression pattern for validating IP version 4 addresses.
         */
        const val IP4_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$"
        /**
         * The regular expression pattern for validating IP version 6 addresses.
         */
        const val IP6_REGEX = "^(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))\$"
    }

    /**
     * Checks if the given IP address is valid.
     *
     * This method uses regular expressions to validate both IPv4 and IPv6 addresses.
     *
     * @param value the IP address to validate
     * @return true if the IP address is valid, false otherwise
     */
    override fun isValid(value: String): Boolean {
        return IP4_REGEX.toRegex().matches(value) || IP6_REGEX.toRegex().matches(value)
    }
}