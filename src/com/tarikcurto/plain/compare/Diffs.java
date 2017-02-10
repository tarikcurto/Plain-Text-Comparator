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
public class Diffs {
    
     /**
     * Differences of strings.
     * Key (i) referr to index difference.
     * Key (j) referr to difference of plain index. 
     */
    private ArrayList<String[]> data = new ArrayList<>();
    
    /**
     * Diffs constructor.
     */
    public Diffs(){
        
    }
    
    /**
     * 
     * @return Group of diffs strings.
     */
    public ArrayList<String[]> get(){
        return data;
    }
    
    /**
     * 
     * @param data Group of diffs strings. 
     */
    public void set(ArrayList<String[]> data){
        this.data = data;
    }
    
    /**
     * 
     * @param data Group of diff strings.
     */
    public void add(String[] data){
        this.data.add(data);
    }
    
    /**
     * Fix last difference group (if exist).
     * 
     * @param equals 
     */
    public void fix(Equals equals)
    {
        
        //Get last indexes.
        int lastEqualK = equals.get().size() - 1;
        int lastDiffK = data.size() - 1;
        
        if(lastEqualK < 0 || lastDiffK < 0){
            return;
        }
        
        //Get last diff group.
        String[] diffGroup = data.get(lastDiffK);
        
        //Get diffs split lengths.
        int lengthNode = diffGroup[0].indexOf(equals.get(lastEqualK));
        int lengthClient = diffGroup[1].indexOf(equals.get(lastEqualK));
        
        //Split diff group.
        String strNode = diffGroup[0].substring(0,lengthNode);
        String strClient = diffGroup[1].substring(0,lengthClient);
        
        //Define diff group.
        String[] diffGroupUpdated = {strNode,strClient};
        
        //Update diff group.
        data.set(lastDiffK, diffGroupUpdated);
    }
}
