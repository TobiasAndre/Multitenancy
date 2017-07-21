package com.multitenancy.teste.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Created by Tobias Andre [tobiasae@gmail.com] on 21/07/2017.
 */
public class TenantHolder implements CurrentTenantIdentifierResolver {

    private ThreadLocal<String> tenantHolder = new ThreadLocal<>();

    public void setTenant(String tenant) {
        this.tenantHolder.set(tenant);
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        //return "base2";
        return tenantHolder.get();
    }

    public void onDestroy() {
        tenantHolder.remove();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
