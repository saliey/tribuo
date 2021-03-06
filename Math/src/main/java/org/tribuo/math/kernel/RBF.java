/*
 * Copyright (c) 2015-2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tribuo.math.kernel;

import com.oracle.labs.mlrg.olcut.config.Config;
import com.oracle.labs.mlrg.olcut.provenance.ConfiguredObjectProvenance;
import com.oracle.labs.mlrg.olcut.provenance.impl.ConfiguredObjectProvenanceImpl;
import org.tribuo.math.la.SparseVector;

/**
 * A Radial Basis Function (RBF) kernel, exp(-gamma*|u-v|^2).
 */
public class RBF implements Kernel {
    private static final long serialVersionUID = 1L;

    @Config(mandatory = true,description="Kernel output = exp(-gamma*|u-v|^2).")
    private double gamma;

    /**
     * For olcut.
     */
    private RBF() {}

    /**
     * A Radial Basis Function (RBF) kernel, exp(-gamma*|u-v|^2).
     * @param gamma The length scale of the gaussian.
     */
    public RBF(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public double similarity(SparseVector a, SparseVector b) {
        return Math.exp(-gamma * Math.pow(a.subtract(b).twoNorm(),2.0));
    }

    @Override
    public String toString() {
        return "RBF(gamma="+gamma+")";
    }

    @Override
    public ConfiguredObjectProvenance getProvenance() {
        return new ConfiguredObjectProvenanceImpl(this,"Kernel");
    }
}
