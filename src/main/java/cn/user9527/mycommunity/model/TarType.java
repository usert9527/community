package cn.user9527.mycommunity.model;

public class TarType {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tartype.id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tartype.type_name
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    private String typeName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tartype.id
     *
     * @return the value of tartype.id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tartype.id
     *
     * @param id the value for tartype.id
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tartype.type_name
     *
     * @return the value of tartype.type_name
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tartype.type_name
     *
     * @param typeName the value for tartype.type_name
     *
     * @mbg.generated Mon Oct 14 10:03:15 CST 2019
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}