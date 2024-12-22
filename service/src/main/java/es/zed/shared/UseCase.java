package es.zed.shared;

public interface UseCase <I, R> {

  R execute(I input);
}
