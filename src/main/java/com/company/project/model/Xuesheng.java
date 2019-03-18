package com.company.project.model;

import javax.persistence.*;

public class Xuesheng {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String sobject;

    private String score;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sobject
     */
    public String getSobject() {
        return sobject;
    }

    /**
     * @param sobject
     */
    public void setSobject(String sobject) {
        this.sobject = sobject;
    }

    /**
     * @return score
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(String score) {
        this.score = score;
    }
}