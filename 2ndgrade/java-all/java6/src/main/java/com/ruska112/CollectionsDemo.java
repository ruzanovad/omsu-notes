package com.ruska112;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionsDemo {
    public static int getCountStringsStartingWith(ArrayList<String> strings, char c) {
        if (strings == null) {
            throw new IllegalArgumentException();
        }
        return (int) strings.stream().filter(x -> x.startsWith(String.valueOf(c))).count();
    }

    public static ArrayList<Human> getNamesakesArrayList(ArrayList<Human> humans, Human namesake) {
        if (humans == null) {
            throw new IllegalArgumentException();
        }
        if (namesake == null) {
            throw new IllegalArgumentException();
        }
        return humans.stream().filter(x -> x.getSurname().equals(namesake.getSurname())).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Human> deleteHumanInArrayList(ArrayList<Human> humans, Human delete) {
        if (humans == null) {
            throw new IllegalArgumentException();
        }
        if (delete == null) {
            throw new IllegalArgumentException();
        }
        return humans.stream().filter(x -> !x.equals(delete)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Set<Integer>> getSetsNotIntersectWith(ArrayList<Set<Integer>> setArrayList, Set<Integer> integerSet) {
        if (setArrayList == null) {
            throw new IllegalArgumentException();
        }
        if (integerSet == null) {
            throw new IllegalArgumentException();
        }
        boolean flag = true;
        ArrayList<Set<Integer>> result = new ArrayList<>(setArrayList.size());
        for (Set<Integer> set : setArrayList) {
            for (Integer num0 : integerSet) {
                for (Integer num1 : set) {
                    if (num0.equals(num1)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                result.add(set);
            }
            flag = true;
        }
        return result;
    }

    public static ArrayList<Human> getMaxAgeHumanArrayList(ArrayList<Human> humans) {
        if (humans == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Human> result = new ArrayList<>(humans.size());
        int maxAge = 0;
        for (Human human : humans) {
            if (human.getAge() > maxAge) {
                maxAge = human.getAge();
            }
        }
        for (Human human : humans) {
            if (human.getAge() == maxAge) {
                result.add(human);
            }
        }
        return result;
    }

    public static ArrayList<Human> getSortedArrayList(Human... humans) {
        if (humans == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < humans.length; i++) {
            for (int j = 0; j < humans.length; j++) {
                if (humans[i].getSurname().compareTo(humans[j].getSurname()) < 0) {
                    Human tmp = humans[i];
                    humans[i] = humans[j];
                    humans[j] = tmp;
                }
            }
        }
        return new ArrayList<>(Arrays.stream(humans).toList());
    }

    public static Set<Human> getHumanSet(Map<Integer, Human> humanMap, Set<Integer> integerSet) {
        if (humanMap == null) {
            throw new IllegalArgumentException();
        }
        if (integerSet == null) {
            throw new IllegalArgumentException();
        }
        Set<Human> result = new HashSet<>();
        for (Integer num : integerSet) {
            if (humanMap.containsKey(num)) {
                result.add(humanMap.get(num));
            }
        }
        return result;
    }

    public static ArrayList<Integer> getAdultId(Map<Integer, Human> humanMap) {
        if (humanMap == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> result = new ArrayList<>();
        Set<Integer> integers = humanMap.keySet();
        for (Integer key : integers) {
            if (humanMap.get(key).getAge() >= 18) {
                result.add(key);
            }
        }
        return result;
    }

    public static Map<Integer, Integer> getAgeMap(Map<Integer, Human> humanMap) {
        if (humanMap == null) {
            throw new IllegalArgumentException();
        }
        Map<Integer, Integer> result = new HashMap<>();
        Set<Integer> keys = humanMap.keySet();
        for (Integer key : keys) {
            result.put(key, humanMap.get(key).getAge());
        }
        return result;
    }

    public static Map<Integer, ArrayList<Human>> getPeersHumanMap(Set<Human> humanSet) {
        if (humanSet == null) {
            throw new IllegalArgumentException();
        }
        HumanComparator humanComparator = new HumanComparator();
        Map<Integer, ArrayList<Human>> result = new HashMap<>();
        ArrayList<Human> humanArrayList = new ArrayList<>();
        int age;
        for (Human human0 : humanSet) {
            age = human0.getAge();
            for (Human human1 : humanSet) {
                if (age == human1.getAge()) {
                    humanArrayList.add(human1);
                }
            }
            humanArrayList.sort(humanComparator);
            result.put(age, new ArrayList<>(humanArrayList));
            humanArrayList.clear();
        }
        return result;
    }

    public static Map<Integer, Map<Character, ArrayList<Human>>> getAgeCharHumanListMap(Set<Human> humanSet) {
        if (humanSet == null) {
            throw new IllegalArgumentException();
        }
        HumanComparator humanComparator = new HumanComparator();
        ArrayList<Human> humanArrayList = new ArrayList<>();
        Map<Character, ArrayList<Human>> characterHumanListMap = new HashMap<>();
        Map<Integer, Map<Character, ArrayList<Human>>> result = new HashMap<>();
        int age;
        char letter;
        var integerHumanListMap = getPeersHumanMap(humanSet);
        for (Map.Entry<Integer, ArrayList<Human>> humansEntry : integerHumanListMap.entrySet()) {
            letter = humansEntry.getValue().get(0).getSurname().charAt(0);
            for (Human human : humansEntry.getValue()) {
                if (human.getSurname().charAt(0) == letter) {
                    humanArrayList.add(human);
                }
            }
            humanArrayList.sort(humanComparator);
            characterHumanListMap.put(letter, new ArrayList<>(humanArrayList));
            humanArrayList.clear();
            result.put(humansEntry.getKey(), new HashMap<>(characterHumanListMap));
            characterHumanListMap.clear();
        }
        return result;
    }
}
