package es.zed.shared.domain.ports.inbound;

public interface UseCase <I, R> {

  R execute(I input);
}
