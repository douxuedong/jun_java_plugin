package org.smartboot.shared;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Wujun
 * @version ToString.java, v 0.1 2015年11月5日 下午3:37:38 Seer Exp. 
 */
public class ToString implements Serializable{

	/** */
	private static final long serialVersionUID = -4640631400893619926L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}

}
