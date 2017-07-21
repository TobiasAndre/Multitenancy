package com.multitenancy.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Tobias Andre [tobiasae@gmail.com] on 21/07/2017.
 */
@Entity
@Table(name = "Pais")
public class Pais implements Serializable {

    @Id
    @Column(name="cd_pais")
    private int cdPais;
    @Column(name="ds_pais")
    private String dsPais;

    public int getCdPais() {

        return cdPais;
    }

    public void setCdPais(int cdPais) {
        this.cdPais = cdPais;
    }

    public String getDsPais() {
        return dsPais;
    }

    public void setDsPais(String dsPais) {
        this.dsPais = dsPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pais pais = (Pais) o;

        if (cdPais != pais.cdPais) return false;
        return dsPais != null ? dsPais.equals(pais.dsPais) : pais.dsPais == null;
    }

    @Override
    public int hashCode() {
        int result = cdPais;
        result = 31 * result + (dsPais != null ? dsPais.hashCode() : 0);
        return result;
    }
}
