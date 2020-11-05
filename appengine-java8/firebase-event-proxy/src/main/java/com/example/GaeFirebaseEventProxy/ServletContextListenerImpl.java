/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.gaefirebaseeventproxy;

import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// ServletContextListener that is called whenever your App Engine app starts up.
public class ServletContextListenerImpl implements ServletContextListener {

  private static final Logger log = Logger.getLogger(ServletContextListener.class.getName());

  @Override
  public void contextInitialized(ServletContextEvent event) {
    log.info("Starting ....");
    com.example.gaefirebaseeventproxy.FirebaseEventProxy proxy = new com.example
        .gaefirebaseeventproxy.FirebaseEventProxy();
    proxy.start();
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
