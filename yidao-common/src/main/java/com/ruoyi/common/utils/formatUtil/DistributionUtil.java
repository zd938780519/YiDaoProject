package com.ruoyi.common.utils.formatUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 外卖配送时间和配送距离工具
 */
public class DistributionUtil {
    /**最大配送范围*/
    public static final int MAX_DISTANCE = 10000;

    /**计算时间初始距离(m)*/
    private static final int TIME_INITIAL = 3000;
    /**计算时间增长距离(m)*/
    private static final int TIME_GROWTH = 200;
    /**初始时间*/
    private static final int TIME = 30;

    /**计算金额初始距离(m)*/
    private static final int FEE_INITIAL = 1000;
    /**计算金额增长距离(m)*/
    private static final int FEE_GROWTH = 1000;
    /**初始金额*/
    private static final int FEE = 2;
    /**
     * 配送时间：3km及以内按30分钟算，每超出0.2km增加一分钟（显示时分）
     * @param location
     * @return 例：19:30
     */
    public static String calculateTime(Double location){
        if(location == null || location < 0)return null;
        int delta_T = TIME;
        //配送时间计算
        if (location > TIME_INITIAL){
            delta_T = (int)(TIME + (location - TIME_INITIAL) / TIME_GROWTH + ((location - TIME_INITIAL) % TIME_GROWTH == 0 ? 0 : 1));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String localTime = LocalTime.now().plusMinutes(delta_T).format(formatter);
        return localTime;
    }
    /**
     * 配送费：1km内按2元算，每超出1km增加0.5元
     * @param location
     * @return 金额（元）
     */
    public static Double calculateMoney(Double location){
        if(location == null || location < 0)return null;
        double money = FEE;
        //配送费计算
        if(location > FEE_INITIAL){
            money = FEE + ((location - FEE_INITIAL) / FEE_GROWTH + ((location - FEE_INITIAL) % FEE_GROWTH == 0 ? 0 : 1)) * 0.5;
        }
        return money;
    }

    /**
     * 配送时间：配送时间：3km及以内按30分钟算，每超出0.2km增加一分钟
     * @param location
     * @return 完整的LocalDateTime类型
     */
    public static LocalDateTime calculateDetailTime(Double location){
        if(location == null || location < 0)return null;
        int delta_T = TIME;
        //配送时间计算
        if (location > TIME_INITIAL){
            delta_T = (int)(TIME + (location - TIME_INITIAL) / TIME_GROWTH + ((location - TIME_INITIAL) % TIME_GROWTH == 0 ? 0 : 1));
        }
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(delta_T);
        return localDateTime;
    }

    /**
     * 配送时间：配送时间：3km及以内按30分钟算，每超出0.2km增加一分钟
     * @param location
     * @return 配送时长（min）
     */
    public static Integer calculateDuration(Double location){
        if(location == null || location < 0)return null;
        int delta_T = TIME;
        //配送时间计算
        if (location > TIME_INITIAL){
            delta_T = (int)(TIME + (location - TIME_INITIAL) / TIME_GROWTH + ((location - TIME_INITIAL) % TIME_GROWTH == 0 ? 0 : 1));
        }
        return delta_T;
    }
}
