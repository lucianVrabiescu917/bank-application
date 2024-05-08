package com.example.bankapplication.utils;

import com.example.bankapplication.enums.RolesEnum;
import com.example.bankapplication.enums.Status;
import lombok.experimental.UtilityClass;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class StatusUtils {
    public static boolean isStatusForImporter(Status status) {
        return IMPORTER_STATUSES.contains(status);
    }

    public static boolean isStatusForBank(Status status) {
        return BANK_STATUSES.contains(status);
    }

    public static boolean isStatusForExporter(Status status) {
        return EXPORTER_STATUSES.contains(status);
    }

    public final Map<Status, List<RolesEnum>> STATUS_ROLES_MAP =  Map.of(
        Status.ACCEPTED, List.of(RolesEnum.BANK, RolesEnum.IMPORTER),
        Status.PENDING, List.of(RolesEnum.BANK),
        Status.DECLINED, List.of(RolesEnum.BANK),
        Status.ACCEPTED_BANK, List.of(RolesEnum.EXPORTER),
        Status.DECLINED_BANK, List.of(RolesEnum.IMPORTER)
        );

    public static final List<Status> EXPORTER_STATUSES = List.of(Status.ACCEPTED_BANK);

    public static final List<Status> BANK_STATUSES = List.of(Status.PENDING, Status.DECLINED, Status.ACCEPTED);

    public static final List<Status> IMPORTER_STATUSES = List.of(Status.ACCEPTED, Status.DECLINED_BANK);
}
