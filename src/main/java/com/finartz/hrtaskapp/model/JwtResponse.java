package com.finartz.hrtaskapp.model;

import java.io.Serializable;
import java.util.Objects;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JwtResponse)) return false;
        JwtResponse that = (JwtResponse) o;
        return Objects.equals(jwttoken, that.jwttoken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwttoken);
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "jwttoken='" + jwttoken + '\'' +
                '}';
    }
}
