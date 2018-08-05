package rascu.stefan.twitchanalysis.data.remote;

public interface ResponseHandler<T> {
    void onSuccess(T response);
    void onError(int responseCode, String message);
}
