package com.example.bankapplication.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UuidUtils {
    public static String generateUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static boolean isValid(String uuid) {
        return uuid != null && !uuid.isEmpty();
    }
}
