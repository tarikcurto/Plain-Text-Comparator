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

import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Tarik Curto - centro.tarik at live.com
 */
public class Comparator implements DataInterface {

    /**
     * Compared strings.
     */
    private ArrayList<String> data = new ArrayList<>();

    /**
     * Instantiated Equals Class.
     */
    public Equals equals = new Equals();

    /**
     * Instantiated Difs Class.
     */
    public Diffs diffs = new Diffs();
    
    /**
     * Comprator constructor.
     */
    public Comparator() {

    }

    /**
     *
     * @return Compared strings.
     */
    @Override
    public ArrayList<String> get() {
        return data;
    }

    /**
     *
     * @param data Compared strings.
     */
    @Override
    public void set(ArrayList<String> data) {
        this.data = data;
    }

    /**
     *
     * @param data Compared string.
     */
    @Override
    public void add(String data) {
        this.data.add(data);
    }

    /**
     * Use the method only when you have defined your data to compare.
     * 
     * @return Comparator
     */
    public Comparator compare() {
        
        compare(data);
        return this;
    }

    /**
     * Compare data to asign work.
     * @param data
     */
    private void compare(ArrayList<String> data) {

        int parallels = checkParallel(data);

        if (parallels > 0) {

            //Process positive coincidence.
            processMatch(parallels, data);

        } else {

            //Process no coincidence.
            tryFindMatch(data);

        }
    }

    /**
     * Process data when there are matches.
     *
     * @param coincidenceLength
     * @param strs
     */
    private void processMatch(int coincidenceLength, ArrayList<String> strs) {

        //Split strings.
        ArrayList<String> splitNode = split(coincidenceLength, strs.get(0));
        ArrayList<String> splitClient = split(coincidenceLength, strs.get(1));

        //Define equals of execution.
        if(splitNode.get(0).length() > 0){
            equals.add(splitNode.get(0));
        }

        //Repair last difference group by last equal.
        diffs.fix(equals);

        //Set differences strings of execution.
        String[] executionDifferences = {
            splitNode.get(1),
            splitClient.get(1)
        };
        
        if(executionDifferences[1].length() > 0 || executionDifferences[0].length() > 0){
            diffs.add(executionDifferences);
        }

        //Re-process string by executionDifferences.
        compare(new ArrayList<>(Arrays.asList(executionDifferences)));
    }

    /**
     * Process data when there aren't matches.
     *
     * @param strs
     */
    private void tryFindMatch(ArrayList<String> strs) {

        //Loop to split node.
        for (int i = 0; i < (strs.get(0)).length(); i++) {

            //Define strNode. Only need split part.
            ArrayList<String> splitStrNode = split(i, strs.get(0));

            //Define strClient.
            String strClient = strs.get(1);

            //Loop to split client and compare with node.
            for (int j = 0; j < strClient.length(); j++) {

                ArrayList<String> strsToCheck = new ArrayList<>();
                
                strsToCheck.add(splitStrNode.get(1));

                ArrayList<String> splitStrClient = split(j, strClient);
                strsToCheck.add(splitStrClient.get(1));

                int coincidence = checkParallel(strsToCheck);

                if (coincidence > 0) {
                    compare(strsToCheck);
                    return;
                }

            }

        }
    }

    /**
     * Check index matches of two strings.
     *
     * @param data
     * @return
     */
    private static int checkParallel(ArrayList<String> data) {

        char[][] splitChars = {
            data.get(0).toCharArray(),
            data.get(1).toCharArray()
        };

        int coincidence = 0;

        for (int i = 0; i < splitChars[0].length; i++) {

            if (splitChars[0][i] == splitChars[1][i]) {

                //Pevent false coincidences.
                if (i == 0 && !isCalibrate(splitChars[0][i], data)) {
                    return 0;
                }

                coincidence++;
            } else {
                break;
            }

        }

        return coincidence;
    }

    /**
     * Method prevent false coincidences by check count of piece in strNode and
     * strClient.
     *
     * @param piece
     * @param strs
     * @return
     */
    private static boolean isCalibrate(char piece, ArrayList<String> strs) {

        String pieceStr = String.valueOf(piece);

        int pieceCountNode = (strs.get(0)).length()
                - (strs.get(0)).replace(pieceStr, "").length();

        int pieceCountClient = (strs.get(1)).length()
                - (strs.get(1)).replace(pieceStr, "").length();

        return pieceCountNode == pieceCountClient;
    }

    /**
     *
     * @param index
     * @param piece
     * @return Index 0: part 1. Index 1: part 2.
     */
    private static ArrayList<String> split(int index, String piece) {

        ArrayList<String> data = new ArrayList<String>() {
            {
                add(piece.substring(0, index));
                add(piece.substring(index));
            }
        };

        return data;
    }

}
