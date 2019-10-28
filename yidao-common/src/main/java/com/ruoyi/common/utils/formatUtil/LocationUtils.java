package com.ruoyi.common.utils.formatUtil;

/**
 * 根据经纬度计算距离工具
 */
public class LocationUtils {
    // 地球赤道半径
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @描述 经纬度获取距离，单位为米
     **/
    public static Double getDistance(Double lng1, Double lat1, Double lng2, Double lat2) {
        if(lng1 == null || lat1 == null || lng2 == null || lat2 == null)return null;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    public static void main(String[] args) {
//        double distance = getDistance(117.297687, 34.363376,
//                117.246281, 34.253163);
//        System.out.println("距离" + distance + "米");

//        String a = "";
//        String content = a.replaceAll("\\s*","");
//        System.out.println(content);

        System.out.println(DistributionUtil.calculateDetailTime(1000.0));

    }

}
