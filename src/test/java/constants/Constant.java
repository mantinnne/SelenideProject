package constants;

import java.util.Arrays;
import java.util.stream.Stream;

public class Constant {
    public static final String[] direction = {"art-creation", "other", "op", "oplpr", "opnonregisteredlittle", "opnonregisteredrights", "opnonregistered", "opopenrussia", "opnavalny", "oprb", "oplb", "polit_non_registered", "polit_registered", "opposeconditionally", "opposeregistered", "opposecritic", "pv", "pvinfo", "pvcommunity", "destructive", "ultras", "aue", "suicid", "anarchism", "cybersport", "dosug", "art", "creation", "education", "self-development", "volunteering", "sport", "positive", "schoolshooting", "systemopposition", "nonsystemopposition", "infatuation", "music", "beauty-health", "humor", "twvesna", "twpa", "twlpr", "additional", "shock", "nationalist", "vagrancy", "radicalpolitical", "militarism", "lgbt", "gambling", "sniffing", "booling", "shok", "nationalists", "religion", "islam", "prank", "acab", "narko", "sects", "depression"};
    public static String requesQuery = "[me, brandNews, schools, schoolsToMap, schoolchildrenWithPagination, schoolchild, schoolchildrenSchools, schoolchildrenCities, schoolInfo, schoolPersonsWithPagination, schoolStatistic, schoolsElastic, schoolStatisticDirection, cities, regions, districts, regionDistricts, info, breadcrumbs, filterCities, filterSchools, statisticCounters, statisticRatings, statisticDirectionsByRegion, dashboardCounters, dashBoardDestructProfilesByDirections, dashBoardProfileColors, dashBoardAllProfiles, dashBoardBSTMProfiles, dashBoardPositiveProfiles, dashBoardDestructProfiles, dashBoardAdditionalDestructProfiles, dashBoardSettings, dashBoardBlocks, reports, reportOperative, swampCounters, reportSchoolRatingProfiles, reportSchoolRatingPersons, reportPostThemes, reportRegionIndex, reportDirectionsByRegion, directions, associations, themes, userReports, crawlerTasks, usersWithPagination, filterReportYear, filterRegions, getBrandByTheme, profilesWithPagination, profiles, profile, profilesElastic, profilesElasticAggRatings, checkProfiles, recoverProfiles, profileFriends, profileFriendsPaginate, profileFriendsNotInDatabase, profileGroupsPaginate, profileGroupsNotInDatabase, profileFriendsWithPagination, profileGroupsWithPagination, profileAssociations, profileNetworkData, profileRetro, profileByLink, profileStatEventsByDates, profileStatEventsByDirections, profileStatEventsByTypes, profileMonitoring, profileRating, profileRatingHistory, profileStatistics, profilePositiveGroups, involvementCount, profilesPagination, checkProfileForSchoolchildrenDossier, profilePdf, group, groupByLink, groupsWithPagination, groupProfiles, groupsMonitoringStatistics, groupsStream, groupRetro, profileOcasEntities, summaryProfilePosts, profilePostsWithPagination, personsWithPagination, person, monitoringInfo, metodic, metodicStartCalculate, metodicsAvailable, metodicsCriteria, statisticsList, availablePermissionModules, DossierList, Dossier, reportByUserList, profileActivityStatistics, profileMarkersStatistics, profileComments, profilePosts, profilePhotos, profileLikes, profileValues]";
    public static String requestMutation = "[logout, saveSchoolchild, removeSchoolchild, schoolchildAddFile, schoolUpdate, saveAnalyticComment, saveBstmDate, createPdf, doProfileAction, savePerson, removePerson, userReportCreate, userReportRemove, saveBstmGroupDate, saveDirection, removeDirection, saveTheme, removeTheme, saveCrawlerTask, saveMonitoring, saveSocialNetworkGroup, saveSocialNetworkProfile, saveProfileMonitoring, updateProfileEvents, dashBoardSettings, dashBoardBlocks, profileReport, reports/PersonReport, subjectReport]";

    public static final String[] regionvalues = {"17", "56", "34", "31", "64", "36", "87", "03", "43", "16", "73", "70", "66", "02", "11", "14", "92", "06", "20", "91", "25", "19", "01", "24", "26", "39", "12", "07", "09", "18", "27", "53", "44", "08", "61", "35", "30", "15", "71", "40", "76", "21", "74", "33", "13", "55", "68", "58", "52", "62", "32", "60", "42", "75", "28", "54", "38", "45", "41", "04", "49", "79", "48", "51", "05", "67", "59", "23", "78", "50", "29", "83", "89", "72", "86", "77", "57", "46", "63", "47", "37", "65", "22", "69", "10"};
    private static final String[] citiesValues = {"191655065", "286084866", "286085444", "286085202", "286085489", "286203959", "286200781", "457354065", "456960757"};
    private static final String[] directionValues = {"art-creation", "other", "op", "oplpr", "opnonregisteredlittle", "opnonregisteredrights", "opnonregistered", "opopenrussia", "opnavalny", "oprb", "oplb", "polit_non_registered", "polit_registered", "opposeconditionally", "opposeregistered", "opposecritic", "pv", "pvinfo", "pvcommunity", "destructive", "ultras", "aue", "suicid", "anarchism", "cybersport", "dosug", "art", "creation", "education", "self-development", "volunteering", "sport", "positive", "schoolshooting", "systemopposition", "nonsystemopposition", "infatuation", "music", "beauty-health", "humor", "twvesna", "twpa", "twlpr", "additional", "shock", "nationalist", "vagrancy", "radicalpolitical", "militarism", "lgbt", "gambling", "sniffing", "booling", "shok", "nationalists", "religion", "islam", "prank", "acab", "narko", "sects", "depression"};
    private static final String[] profileValues = {"8", "21", "26", "36", "38", "50", "64", "66", "88", "94"};
    private static final String[] cosialNetworkValue = {"Vkontakte", "Odnoklassniki", "Twitter", "Telegram", "Instagram", "Youtube", "Facebook"};
    public static final String[] metodicValue = {"34", "35"};


    public static String setRequesQuery() {
        return requesQuery;
    }

    public static String setRequestMutation() {
        return requestMutation;
    }

    public static String[] setDirection() {
        return direction;
    }

    static Stream rangeRegion() {
        return Arrays.stream(regionvalues);
    }

    static Stream rangeCities() {
        return Arrays.stream(citiesValues);
    }

    static Stream rangeDirection() {
        return Arrays.stream(directionValues);
    }

    static Stream rangeProfile() {
        return Arrays.stream(profileValues);
    }

    public static Stream rangeMetodic() {
        return Arrays.stream(metodicValue);
    }

    static Stream rangeCosialNetwork() {
        return Arrays.stream(cosialNetworkValue);
    }

}








