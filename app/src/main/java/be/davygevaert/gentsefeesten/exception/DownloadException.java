package be.davygevaert.gentsefeesten.exception;

/**
 * Created by Davy on 6/12/2015.
 */
public class DownloadException extends Exception {
    /* constructor 1 */
    public DownloadException(String message) {
        super(message);
    }

    /* constructor 2 */
    public DownloadException(String message, Throwable cause) {

    }
}
