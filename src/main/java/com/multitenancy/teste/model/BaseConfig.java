package com.multitenancy.teste.model;

import javax.persistence.*;

/**
 * Created by Tobias Andre [tobiasae@gmail.com] on 21/07/2017.
 */
@Entity
@Table(name = "BaseConfig")
@NamedQueries({@NamedQuery(name = "BaseConfig.findAll", query = "SELECT * FROM BaseConfig ")})
public class BaseConfig {

    @Id
    @Column(name="id_config")
    private int idConfig;
    @Column(name="nm_base")
    private String nmBase;
    @Column(name="ds_email")
    private String dsEmail;

    public int getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(int idConfig) {
        this.idConfig = idConfig;
    }

    public String getNmBase() {
        return nmBase;
    }

    public void setNmBase(String nmBase) {
        this.nmBase = nmBase;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseConfig that = (BaseConfig) o;

        if (idConfig != that.idConfig) return false;
        if (!nmBase.equals(that.nmBase)) return false;
        return dsEmail.equals(that.dsEmail);
    }

    @Override
    public int hashCode() {
        int result = idConfig;
        result = 31 * result + nmBase.hashCode();
        result = 31 * result + dsEmail.hashCode();
        return result;
    }
}
