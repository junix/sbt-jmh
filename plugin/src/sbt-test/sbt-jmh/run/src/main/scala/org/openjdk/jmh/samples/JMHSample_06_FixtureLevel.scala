/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.jmh.samples

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
class JMHSample_06_FixtureLevel {

  var x: Double = _

  /*
   * Fixture methods have different levels to control when they are about to run.
   * There are at least three Levels available at user expense. These are, from
   * the top to bottom:
   *
   * Level.Trial:      before or after the entire benchmark run (the sequence of iterations)
   * Level.Iteration:  before or after the benchmark iteration (the sequence of invocations)
   * Level.Invocation; before or after the benchmark method invocation (WARNING: read the Javadoc before using)
   *
   * Time spent in fixture methods does not count into the performance metrics,
   * so you can use this to do some heavy-lifting.
   */

  @TearDown(Level.Iteration)
  def check: Unit = assert(x > Math.PI, "Nothing changed?")

  @Benchmark
  def measureRight: Unit = x += 1

  @Benchmark
  def measureWrong: Unit = {
    var x = 0.0
    x += 1
  }

}
