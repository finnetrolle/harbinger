package ru.finnetrolle.scripting.harbinger.util;

import java.util.Objects;

/**
 * Yet another implementation of tuple
 * @param <K> key
 * @param <V> value
 */
public class Pair<K, V> {

  private final K key;
  private final V value;

  private Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public static <K, V> Pair<K, V> from(K key, V value) {
    return new Pair<>(key, value);
  }

  @Override
  public String toString() {
    return "Pair{" +
        "key=" + key +
        ", value=" + value +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(key, pair.key) &&
        Objects.equals(value, pair.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}