package cn.user9527.mycommunity.model;

import java.io.Serializable;

public class Tar implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tar.id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tar.tar_name
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    private String tarName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tar.typt_id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    private Integer typtId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tar.id
     *
     * @return the value of tar.id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tar.id
     *
     * @param id the value for tar.id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tar.tar_name
     *
     * @return the value of tar.tar_name
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public String getTarName() {
        return tarName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tar.tar_name
     *
     * @param tarName the value for tar.tar_name
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public void setTarName(String tarName) {
        this.tarName = tarName == null ? null : tarName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tar.typt_id
     *
     * @return the value of tar.typt_id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public Integer getTyptId() {
        return typtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tar.typt_id
     *
     * @param typtId the value for tar.typt_id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public void setTyptId(Integer typtId) {
        this.typtId = typtId;
    }
}
