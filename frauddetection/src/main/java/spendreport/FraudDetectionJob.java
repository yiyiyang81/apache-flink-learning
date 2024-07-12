/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spendreport;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.apache.flink.walkthrough.common.source.TransactionSource;

/**
 * Skeleton code for the datastream walkthrough
 */
public class FraudDetectionJob {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		

		DataStream<Transaction> transactions = env
			.addSource(new TransactionSource()) //Sources ingest data from external systems
			.name("transactions"); //for debugging purposes

		DataStream<Alert> alerts = transactions
			// Fraud occurs on a per-account basis, so we need to group transactions by account ID
			// to ensure that all transactions for the same account are processed by the same parallel task of the fraud detector operator
			.keyBy(Transaction::getAccountId)
			.process(new FraudDetector()) // immediately after a KeyBy operation, we can say the key is executed within a keyed context
			.name("fraud-detector");

		alerts
			.addSink(new AlertSink()) //Sinks write data to external systems
			.name("send-alerts");

		env.execute("Fraud Detection");
	}
}
