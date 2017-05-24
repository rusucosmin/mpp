package com.snowcoders.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends com.snowcoders.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Client, String> name;
	public static volatile SetAttribute<Client, Order> orders;

}

