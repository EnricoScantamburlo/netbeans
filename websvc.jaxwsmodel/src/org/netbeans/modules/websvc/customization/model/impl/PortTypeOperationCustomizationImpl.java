/*
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
*
* Oracle and Java are registered trademarks of Oracle and/or its affiliates.
* Other names may be trademarks of their respective owners.
*
* The contents of this file are subject to the terms of either the GNU
* General Public License Version 2 only ("GPL") or the Common
* Development and Distribution License("CDDL") (collectively, the
* "License"). You may not use this file except in compliance with the
* License. You can obtain a copy of the License at
* http://www.netbeans.org/cddl-gplv2.html
* or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
* specific language governing permissions and limitations under the
* License.  When distributing the software, include this License Header
* Notice in each file and include the License file at
* nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
* particular file as subject to the "Classpath" exception as provided
* by Oracle in the GPL Version 2 section of the License file that
* accompanied this code. If applicable, add the following below the
* License Header, with the fields enclosed by brackets [] replaced by
* your own identifying information:
* "Portions Copyrighted [year] [name of copyright owner]"
*
* Contributor(s):
*
* The Original Software is NetBeans. The Initial Developer of the Original
* Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
* Microsystems, Inc. All Rights Reserved.
*
* If you wish your version of this file to be governed by only the CDDL
* or only the GPL Version 2, indicate your decision by adding
* "[Contributor] elects to include this software in this distribution
* under the [CDDL or GPL Version 2] license." If you do not indicate a
* single choice of license, a recipient has the option to distribute
* your version of this file under either the CDDL, the GPL Version 2 or
* to extend the choice of license to its licensees as provided above.
* However, if you add GPL Version 2 code and therefore, elected the GPL
* Version 2 license, then the option applies only if the new code is
* made subject to such option by the copyright holder.
*/
/*
 * PortTypeOperationCustomizationImpl.java
 *
 * Created on February 4, 2006, 2:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.websvc.customization.model.impl;

import org.netbeans.modules.websvc.api.customization.model.JAXWSQName;
import java.util.Collection;
import org.netbeans.modules.websvc.api.customization.model.EnableAsyncMapping;
import org.netbeans.modules.websvc.api.customization.model.EnableWrapperStyle;
import org.netbeans.modules.websvc.api.customization.model.JavaMethod;
import org.netbeans.modules.websvc.api.customization.model.JavaParameter;
import org.netbeans.modules.websvc.api.customization.model.PortTypeOperationCustomization;
import org.netbeans.modules.xml.wsdl.model.WSDLModel;
import org.netbeans.modules.xml.wsdl.model.visitor.WSDLVisitor;

import org.w3c.dom.Element;

/**
 *
 * @author Roderico Cruz
 */
public class PortTypeOperationCustomizationImpl extends CustomizationComponentImpl
    implements PortTypeOperationCustomization{
    
    /**
     * Creates a new instance of PortTypeOperationCustomizationImpl
     */
    public PortTypeOperationCustomizationImpl(WSDLModel model, Element e) {
        super(model, e);
    }
    
    public PortTypeOperationCustomizationImpl(WSDLModel model){
        this(model, createPrefixedElement(JAXWSQName.BINDINGS.getQName(), model));
    }

    public void setEnableAsyncMapping(EnableAsyncMapping async) {
        appendChild(ENABLE_ASYNC_MAPPING_PROPERTY, async);
    }

    public void removeJavaParameter(JavaParameter parameter) {
        removeChild(JAVA_PARAMETER_PROPERTY, parameter);
    }

    public void addJavaParameter(JavaParameter parameter) {
        appendChild(JAVA_PARAMETER_PROPERTY, parameter);
    }

    public void setJavaMethod(JavaMethod method) {
        appendChild(JAVA_METHOD_PROPERTY, method);
    }

    public void setEnableWrapperStyle(EnableWrapperStyle wrapperStyle) {
        appendChild(ENABLE_WRAPPER_STYLE_PROPERTY, wrapperStyle);
    }

    public Collection<JavaParameter> getJavaParameters() {
        return getChildren(JavaParameter.class);
    }

    public JavaMethod getJavaMethod() {
        return getChild(JavaMethod.class);
    }

    public EnableWrapperStyle getEnableWrapperStyle() {
        return getChild(EnableWrapperStyle.class);
    }

    public EnableAsyncMapping getEnableAsyncMapping() {
       return getChild(EnableAsyncMapping.class);
    }

    public void removeEnableAsyncMapping(EnableAsyncMapping async) {
        removeChild(ENABLE_ASYNC_MAPPING_PROPERTY, async);
    }

    public void removeJavaMethod(JavaMethod method) {
        removeChild(JAVA_METHOD_PROPERTY, method);
    }

    public void removeEnableWrapperStyle(EnableWrapperStyle wrapperStyle) {
        removeChild(this.ENABLE_WRAPPER_STYLE_PROPERTY, wrapperStyle);
    }

    public void accept(WSDLVisitor visitor) {
        visitor.visit(this);
    }
    
}