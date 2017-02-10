/*
 * Copyright 2016 Tarik Curto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tarikcurto.plain.compare;

import java.util.ArrayList;

/**
 *
 * @author Tarik Curto - centro.tarik at live.com
 */
public class Equals implements DataInterface{
    
    /**
     * Differences of strings.
     * Key referr to index difference.
     */
    private ArrayList<String> data = new ArrayList<>();
    
    /**
     * Equals constructor.
     */
    public Equals(){
        
    }
    
    /**
     * 
     * @return Equals strings.
     */
    @Override
    public ArrayList<String> get(){
        return data;
    }
    
    /**
     * 
     * @return Equal strings by index.
     */
    public String get(int index){
        return data.get(index);
    }
    
    /**
     * 
     * @param data Equals strings. 
     */
    @Override
    public void set(ArrayList<String> data){
        this.data = data;
    }
    
    /**
     * 
     * @param data Equal string. 
     */
    @Override
    public void add(String data){
        this.data.add(data);
    }
    
}
