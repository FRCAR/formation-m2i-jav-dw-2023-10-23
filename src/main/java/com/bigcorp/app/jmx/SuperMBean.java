package com.bigcorp.app.jmx;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

/**
 * Gère une valeur qui peut s'incrémenter. Est exportable via JMX car implémente
 * DynamicBean. Peut donc être gérée en externe de la JVM (sur la même machine
 * ou en réseau).
 */
public class SuperMBean implements DynamicMBean {

	private Integer value = 0;

	public void increment() {
		value++;
	}

	public void reset() {
		value = 0;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException,
			ReflectionException {
		if (attribute.equals("value")) {
			return this.value;
		}
		throw new AttributeNotFoundException("Attribut incorrect");
	}

	@Override
	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException,
			MBeanException, ReflectionException {
		if (attribute.getName().equals("value")) {
			if (attribute.getValue() instanceof Integer) {
				this.value = (Integer) attribute.getValue();
			}
			throw new InvalidAttributeValueException("value doit être une instance de Integer");
		}
		throw new AttributeNotFoundException("Attribut incorrect");
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		AttributeList attributeList = new AttributeList();
		attributeList.add(new Attribute("value", this.value));
		return attributeList;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException,
			ReflectionException {
		if(actionName.equals("getValue")) {
			return getValue();
		}
		if(actionName.equals("setValue") && params.length > 0 && params[0] instanceof Integer) {
			setValue((Integer)params[0]);
		}
		if(actionName.equals("reset")) {
			reset();
			return null;
		}
		if(actionName.equals("increment")) {
			increment();
			return null;
		}
		return null;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		try {
			return new MBeanInfo(this.getClass().getName(),
					"Simple bean de démonstration JMX",
					new MBeanAttributeInfo[] {
							new MBeanAttributeInfo(
									"value",
									"Integer",
									"value",
									true,
									true,
									false)
					}, null,
					new MBeanOperationInfo[] {
							new MBeanOperationInfo("Incrémente la valeur de 1", this.getClass().getMethod("increment")),
							new MBeanOperationInfo("Remet la valeur à 0", this.getClass().getMethod("reset")),
							new MBeanOperationInfo("Récupère la valeur", this.getClass().getMethod("getValue")),
							new MBeanOperationInfo("Met la valeur à une certaine valeur", this.getClass().getMethod("setValue", Integer.class))
					}, null);
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
