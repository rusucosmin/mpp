package com.snowcoders.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ extends com.snowcoders.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, Double> price;
	public static volatile SetAttribute<Book, Order> orders;
	public static volatile SingularAttribute<Book, String> title;

}

