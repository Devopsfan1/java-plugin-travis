/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.advisor.plugin.service;

import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ibm.cloud.objectstorage.ClientConfiguration;
import com.ibm.cloud.objectstorage.auth.AWSCredentials;
import com.ibm.cloud.objectstorage.auth.AWSStaticCredentialsProvider;
import com.ibm.cloud.objectstorage.auth.BasicAWSCredentials;
import com.ibm.cloud.objectstorage.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3ClientBuilder;
import com.ibm.cloud.objectstorage.services.s3.model.GetObjectRequest;
import com.ibm.cloud.objectstorage.services.s3.model.S3Object;

@Service
public class S3Service {
	protected static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Value("${advisor.file.import.s3.accesskey}")
	private String accessKey;
	@Value("${advisor.file.import.s3.secretkey}")
	private String secretKey;
	@Value("${advisor.file.import.s3.url}")
	private String endpointUrl;
	@Value("${advisor.file.import.s3.bucket}")
	private String bucketName;
	private AmazonS3 s3;

	public void connect() throws IOException {
		final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		ClientConfiguration clientConfig = new ClientConfiguration().withRequestTimeout(5000);
		clientConfig.setUseTcpKeepAlive(true);
		this.s3 =
				AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withEndpointConfiguration(new EndpointConfiguration(endpointUrl,"global"))
				.withPathStyleAccessEnabled(true)
				.withClientConfiguration(clientConfig)
				.build();
	}

	public String getFileByName(String itemName) {
		S3Object item = s3.getObject(new GetObjectRequest(bucketName, itemName));
		try {
			final int bufferSize = 1024;
			final char[] buffer = new char[bufferSize];
			final StringBuilder out = new StringBuilder();
			InputStreamReader in = new InputStreamReader(item.getObjectContent());
			for (; ; ) {
				int rsz = in.read(buffer, 0, buffer.length);
				if (rsz < 0)
					break;
				out.append(buffer, 0, rsz);
			}
			return out.toString();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return "";
	}
}
