package com.dunzo.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * created by Jay Doshi
 * Date: 10/12/20
 **/
@Data
@AllArgsConstructor
public class Pair<K, V> {
    K key;
    V value;
}
