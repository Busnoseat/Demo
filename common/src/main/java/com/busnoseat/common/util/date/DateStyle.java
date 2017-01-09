package com.busnoseat.common.util.date;

/**
 * The enum Date style.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public enum DateStyle {
    /**
     *Mm dd date style.
     */
    MM_DD("MM-dd"), /**
     *Yyyy mm date style.
     */
    YYYY_MM("yyyy-MM"), /**
     *Yyyy mm dd date style.
     */
    YYYY_MM_DD("yyyy-MM-dd"), /**
     * The Mm dd hh mm.
     */
    MM_DD_HH_MM("MM-dd HH:mm"), /**
     * The Mm dd hh mm ss.
     */
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"), /**
     * The Yyyy mm dd hh mm.
     */
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"), /**
     * The Yyyy mm dd hh mm ss.
     */
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

    /**
     *Mm dd en date style.
     */
    MM_DD_EN("MM/dd"), /**
     *Yyyy mm en date style.
     */
    YYYY_MM_EN("yyyy/MM"), /**
     *Yyyy mm dd en date style.
     */
    YYYY_MM_DD_EN("yyyy/MM/dd"), /**
     * The Mm dd hh mm en.
     */
    MM_DD_HH_MM_EN("MM/dd HH:mm"), /**
     * The Mm dd hh mm ss en.
     */
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"), /**
     * The Yyyy mm dd hh mm en.
     */
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"), /**
     * The Yyyy mm dd hh mm ss en.
     */
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),

    /**
     *Mm dd cn date style.
     */
    MM_DD_CN("MM月dd日"), /**
     *Yyyy mm cn date style.
     */
    YYYY_MM_CN("yyyy年MM月"), /**
     *Yyyy mm dd cn date style.
     */
    YYYY_MM_DD_CN("yyyy年MM月dd日"), /**
     *Mm dd hh mm cn date style.
     */
    MM_DD_HH_MM_CN("MM月dd日 HH:mm"), /**
     *Mm dd hh mm ss cn date style.
     */
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"), /**
     *Yyyy mm dd hh mm cn date style.
     */
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"), /**
     *Yyyy mm dd hh mm ss cn date style.
     */
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),

    /**
     *Hh mm date style.
     */
    HH_MM("HH:mm"), /**
     *Hh mm ss date style.
     */
    HH_MM_SS("HH:mm:ss");

    private String value;

    private boolean isShowOnly;

    DateStyle(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Is show only boolean.
     *
     * @return the boolean
     */
    public boolean isShowOnly() {
        return isShowOnly;
    }
}
