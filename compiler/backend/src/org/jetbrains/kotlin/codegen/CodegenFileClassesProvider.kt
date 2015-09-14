/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.codegen

import org.jetbrains.kotlin.fileClasses.JvmFileClassInfo
import org.jetbrains.kotlin.fileClasses.JvmFileClassUtil
import org.jetbrains.kotlin.fileClasses.JvmFileClassesProvider
import org.jetbrains.kotlin.psi.JetFile

public class CodegenFileClassesProvider(private val packageFacadesAsMultifileClasses: Boolean) : JvmFileClassesProvider {
    override public fun getFileClassInfo(file: JetFile): JvmFileClassInfo {
        val fileClassInfo = JvmFileClassUtil.getFileClassInfoNoResolve(file)
        if (packageFacadesAsMultifileClasses && !fileClassInfo.isMultifileClass) {
            return JvmFileClassUtil.getMultifilePackageFacadePartInfo(file)
        }
        else {
            return fileClassInfo
        }
    }
}