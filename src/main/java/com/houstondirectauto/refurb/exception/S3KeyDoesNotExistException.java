package com.houstondirectauto.refurb.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S3KeyDoesNotExistException extends Exception {
    /**
     * S3KeyDoesNotExistException Method
     * 
     * @param bucketName
     * @param key
     */
    public S3KeyDoesNotExistException(String bucketName, String key) {
        super( String.format("The key %s does not exist in bucket %s", bucketName, key));
        log.debug( String.format("The key %s does not exist in bucket %s", bucketName, key));
    }

}
