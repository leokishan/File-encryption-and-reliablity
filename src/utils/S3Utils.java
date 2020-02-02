package utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;

public class S3Utils {

	protected Boolean checkForBucket(AmazonS3 amazonS3, String bucket)
			throws AmazonClientException, AmazonServiceException {
		try {
			return amazonS3.doesBucketExist(bucket);
		} catch (Exception ex) {
			throw ex;
		}
	}

	protected Boolean checkForObject(AmazonS3 amazonS3, String bucketName, String key)
			throws AmazonServiceException, AmazonClientException {
		try {
			checkForBucket(amazonS3, bucketName);

			if (amazonS3.doesObjectExist(bucketName, key))
				return true;
			else
				return false;

		} catch (Exception ex) {
			throw ex;
		}
	}

}
