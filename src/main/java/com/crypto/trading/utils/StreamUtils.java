/**
 * StreamUtils.java
 * 
 * Copyright(c)2010 by Soreco AG, CH-8603 Schwerzenbach. http://www.soreco.ch
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of 
 * Soreco AG. You shall not disclose such confidential information and 
 * shall use it only in accordance with the terms of the license 
 * agreement you entered into with Soreco.
 *
 * @author qtdan
 */
package com.crypto.trading.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class StreamUtils {
	/**
	 * Return stream of @this collection or an empty stream if @this collection is null
	 * @param collection
	 * @return
	 */
	public static <T> Stream<T> safeStream(Collection<T> collection){
		return Optional.ofNullable(collection).orElse(Collections.emptyList()).stream();
	}
	
	/**
	 * Convert iterable to list
	 * @param iterable
	 * @return
	 */
    public static <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
