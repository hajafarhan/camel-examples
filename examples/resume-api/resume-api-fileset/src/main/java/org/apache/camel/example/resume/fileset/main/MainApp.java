/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.example.resume.fileset.main;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.caffeine.resume.CaffeineCache;
import org.apache.camel.example.resume.strategies.kafka.KafkaUtil;
import org.apache.camel.example.resume.strategies.kafka.check.CheckRoute;
import org.apache.camel.example.resume.strategies.kafka.fileset.LargeDirectoryRouteBuilder;
import org.apache.camel.main.Main;
import org.apache.camel.processor.resume.kafka.SingleNodeKafkaResumeStrategy;
import org.apache.camel.resume.Resumable;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();

        SingleNodeKafkaResumeStrategy<Resumable> resumeStrategy = KafkaUtil.getDefaultStrategy();
        RouteBuilder routeBuilder = new LargeDirectoryRouteBuilder(resumeStrategy, new CaffeineCache<>(10000));

        main.configure().addRoutesBuilder(new CheckRoute());
        main.configure().addRoutesBuilder(routeBuilder);

        main.run(args);
    }
}

