package es.zed.shared.rest.handlers;

@FunctionalInterface
public interface Handler<T, E> {
  T handle(E event);
}
