/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package demoapp.dom.types.isis.images.samples;

import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import org.apache.isis.applib.value.Image;

import demoapp.dom.types.Samples;
import demoapp.dom.types.javaawt.images.JavaAwtImageService;
import demoapp.dom.types.javaawt.images.samples.JavaAwtImagesSamples;

@Service
public class IsisImagesSamples implements Samples<Image> {

    @Override
    public Stream<Image> stream() {
        return javaAwtImagesSamples.stream()
                .map(javaAwtImageService::javaAwtImageToPixels)
                .map(org.apache.isis.applib.value.Image::new);
    }


    @Inject
    JavaAwtImagesSamples javaAwtImagesSamples;

    @Inject
    JavaAwtImageService javaAwtImageService;

}
