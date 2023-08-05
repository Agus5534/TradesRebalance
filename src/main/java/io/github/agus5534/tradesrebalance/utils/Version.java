package io.github.agus5534.tradesrebalance.utils;


import org.bukkit.Bukkit;

import java.util.regex.Pattern;

/**
 * @author <a href="https://unnamed.team">Unnamed Team</a>
 */
public class Version {
    public static final String VERSION_STRING = Bukkit.getServer().getClass().getName().split(Pattern.quote("."))[3];
    public static final Version CURRENT;
    private final byte major;
    private final byte minor;
    private final byte patch;

    public Version(byte major, byte minor, byte patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public byte getMajor() {
        return this.major;
    }

    public byte getMinor() {
        return this.minor;
    }

    public byte getPatch() {
        return this.patch;
    }

    public String toString() {
        return "v" + this.major + '_' + this.minor + "_R" + this.patch;
    }

    public static Version getVersionOfString(String versionString) {
        String[] args = versionString.split(Pattern.quote("_"));
        byte major = Byte.parseByte(args[0].substring(1));
        byte minor = Byte.parseByte(args[1]);
        byte patch = Byte.parseByte(args[2].substring(1));
        return new Version(major, minor, patch);
    }

    static {
        CURRENT = getVersionOfString(VERSION_STRING);
    }
}
