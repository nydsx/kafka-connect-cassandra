/*
 * Copyright 2016 Tuplejump
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tuplejump.kafka.connect.cassandra

import org.apache.kafka.common.config.ConfigException

class ConnectorLikeSpec extends AbstractSpec with ConnectorLike {
  "ConnectorLike" must {
    "validate and log configuration failure of a CassandraSource on startup" in {
      an[ConfigException] should be thrownBy {
        configure(Map.empty[String, String], classOf[CassandraSourceTask])
      }
    }
    "validate and log valid configuration of a CassandraSource on startup" in {
      val query = "SELECT * FROM test.playlists"
      val topic = "test"
      val config = sourceConfig(query, topic)

      configure(config, classOf[CassandraSourceTask])
    }
    "validate and log configuration failure of a CassandraSink on startup" in {
      an[ConfigException] should be thrownBy {
        configure(Map.empty[String, String], classOf[CassandraSinkTask])
      }
    }
    "validate and log valid configuration of a CassandraSink on startup" in {
      val topicName = "test_kv_topic"
      val tableName = "test.kv"
      val config = sinkConfig((topicName, tableName))

      configure(config, classOf[CassandraSinkTask])
    }
  }
}