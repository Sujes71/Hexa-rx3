package es.zed.shared.rest.handlers;

import io.reactivex.rxjava3.core.Single;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public abstract class RestHandler {

  private static final RestTemplate restTemplate = new RestTemplate();

  public static <T> Single<T> doCall(final String url, final HttpMethod httpMethod, final HttpHeaders httpHeaders, final Object body, final Class<T> responseClass) {
    return Single.defer(() -> {
      log.info("Do call {}, method {}", url, httpMethod);

      try {
        RequestEntity<Object> request = new RequestEntity<>(body, httpHeaders, httpMethod, createUri(url));
        ResponseEntity<T> response = restTemplate.exchange(request, responseClass);
        return Single.just(extractResponseData(response));
      } catch (ResourceAccessException ex) {
        log.error("Connection error occurred: {}", ex.getMessage(), ex);
        return Single.error(new Exception("Connection error occurred", ex));
      } catch (Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return Single.error(new RuntimeException("Unexpected error occurred", ex));
      }
    });
  }

  private static URI createUri(final String uriPath) throws Exception {
    try {
      return new URI(uriPath);
    } catch (URISyntaxException ex) {
      throw new Exception("Uri generator error occurred", ex);
    }
  }

  private static <T> T extractResponseData(final ResponseEntity<T> responseEntity) {
    return responseEntity.getBody();
  }
}