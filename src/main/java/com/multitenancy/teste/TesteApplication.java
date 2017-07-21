package com.multitenancy.teste;

import com.multitenancy.teste.config.TenantHolder;
import com.multitenancy.teste.model.Pais;
import com.multitenancy.teste.repository.PaisRepository;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(
		basePackageClasses = TesteApplication.class,
		entityManagerFactoryRef = "customEntityManagerFactory",
		transactionManagerRef = "customTransactionManager")
public class TesteApplication {

	@Autowired
	private PaisRepository paisRepository;

	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
	}

	public
	@Bean
	PlatformTransactionManager customTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	public
	@Bean
	EntityManagerFactory customEntityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false); // turn off with Discriminator strategy so far!
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.nobresistemas.faturamento.model");
		factory.setPersistenceUnitName("NobrePU");
		factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factory.setDataSource(dataSource);
		factory.getJpaPropertyMap().put(Environment.DIALECT, PostgreSQL94Dialect.class.getName());
		factory.getJpaPropertyMap().put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		factory.getJpaPropertyMap().put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, new TenantHolder());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@RequestMapping(value ="/Paises/{dsuser}", method= RequestMethod.GET)
	@ResponseBody
	List<Pais> listaPaises(@PathVariable("dsuser")String dsuser){


		List<Pais> mPaises = paisRepository.findAll();

		return mPaises;
	}
}
