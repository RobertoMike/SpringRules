package io.github.robertomike.jakidate.constraints.web

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.jakidate.validations.web.ip.Ip

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
        const val IP6_REGEX = "^(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|" + // Full form
                "([0-9a-fA-F]{1,4}:){1,7}:|" +                  // Compressed with trailing ::
                "([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|" +  // Compressed with one omitted group
                "([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|" +
                "([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|" +
                "([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|" +
                "([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|" +
                "[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|" +
                ":((:[0-9a-fA-F]{1,4}){1,7}|:)|" +              // Leading ::
                "fe80:(:[0-9a-fA-F]{0,4}){0,4}%\\w+|" +       // Link-local with zone index
                "::(ffff(:0{1,4})?:)?" +                        // IPv4-mapped IPv6
                "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" + // Embedded IPv4 (valid range 0–255)
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)|" +
                "([0-9a-fA-F]{1,4}:){1,4}:" +
                "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))" +
                "(/([0-9]|[1-9][0-9]|1[01][0-9]|12[0-8]))?$"  // Optional CIDR suffix (/0 to /128)
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