/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.test;

import com.liferay.portal.kernel.test.AggregateTestRule;
import com.liferay.portal.kernel.test.BaseTestRule;
import com.liferay.portal.test.log.LogAssertionTestRule;
import com.liferay.portal.test.randomizerbumpers.UniqueStringRandomizerBumper;
import com.liferay.portal.test.rule.DeleteAfterTestRunTestRule;
import com.liferay.portal.test.util.ClearThreadLocalExecutor;
import com.liferay.portal.test.util.ClearThreadLocalExecutorImpl;
import com.liferay.portal.test.util.InitTestLiferayContextExecutor;
import com.liferay.portal.test.util.InitTestLiferayContextExecutorImpl;

import org.junit.rules.TestRule;
import org.junit.runner.Description;

/**
 * @author Shuyang Zhou
 */
public class LiferayIntegrationTestRule extends AggregateTestRule {

	public LiferayIntegrationTestRule() {
		super(
			false, LogAssertionTestRule.INSTANCE, _clearThreadLocalTestRule,
			_uniqueStringRandomizerBumperTestRule,
			new DeleteAfterTestRunTestRule());

		_initTestLiferayContextExecutor.init();
	}

	private static final ClearThreadLocalExecutor _clearThreadLocalExecutor =
		new ClearThreadLocalExecutorImpl();

	private static final TestRule _clearThreadLocalTestRule =
		new BaseTestRule<Object, Object>() {

			@Override
			protected void afterClass(Description description, Object object) {
				_clearThreadLocalExecutor.clearThreadLocal();
			}

		};

	private static final InitTestLiferayContextExecutor
		_initTestLiferayContextExecutor =
			new InitTestLiferayContextExecutorImpl();

	private static final TestRule _uniqueStringRandomizerBumperTestRule =
		new BaseTestRule<Object, Object>() {

			@Override
			protected Object beforeClass(Description description) {
				UniqueStringRandomizerBumper.reset();

				return null;
			}

		};

}