/*
 * Copyright 2023 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.epam.reportportal.model.activity.event;

import com.epam.reportportal.model.activity.Activity;
import com.epam.reportportal.model.activity.ActivityEvent;
import com.epam.reportportal.model.activity.enums.ActivityAction;
import com.epam.reportportal.model.activity.enums.EventAction;
import com.epam.reportportal.model.activity.enums.EventObject;
import com.epam.reportportal.model.activity.enums.EventPriority;
import com.epam.reportportal.model.activity.enums.EventSubject;
import org.apache.commons.lang3.StringUtils;

/**
 * Publish an event when user is deleted.
 *
 * @author Ryhor_Kukharenka
 */
public class UserDeletedEvent implements ActivityEvent {

  private final int countUsersDeleted;

  public UserDeletedEvent(int countUsersDeleted) {
    this.countUsersDeleted = countUsersDeleted;
  }

  @Override
  public Activity toActivity() {
    return Activity.builder()
        .addCreatedNow()
        .addAction(EventAction.BULK_DELETE)
        .addEventName(ActivityAction.BULK_DELETE_USERS.getValue())
        .addObjectName(getFormatText())
        .addObjectType(EventObject.USER)
        .addSubjectName(EventSubject.APPLICATION.getApplicationName())
        .addSubjectType(EventSubject.APPLICATION)
        .addPriority(EventPriority.HIGH)
        .build();
  }

  private String getFormatText() {
    String userWord = "user" + (countUsersDeleted == 1 ? StringUtils.EMPTY : "s");
    return String.format("%d deleted %s", countUsersDeleted, userWord);
  }

}
