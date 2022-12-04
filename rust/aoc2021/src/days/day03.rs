use std::collections::{HashMap, HashSet};
use std::ops::Index;

fn read_input(input: &str) -> Vec<Vec<bool>> {
    input
        .lines()
        .map(|l| l.chars().map(|c| c == '1').collect::<Vec<bool>>())
        .collect()
}

fn part_one(input: &Vec<Vec<bool>>) -> u32 {
    let num_size = input[0].len();
    let bit_counts = input.iter().fold(vec![0; num_size], |mut counts, binary| {
        binary.iter().enumerate().for_each(|(bit_pos, bit_set)| {
            if *bit_set {
                counts[bit_pos] += 1;
            }
        });
        counts
    });

    let mut gamma: u32 = 0;
    let mut epsilon: u32 = 0;
    let mut multiplier: u32 = 1;
    for i in (0..bit_counts.len()).rev() {
        if bit_counts[i] >= input.len() / 2 {
            gamma += multiplier;
        } else {
            epsilon += multiplier;
        }
        multiplier *= 2;
    }
    gamma * epsilon
}

fn part_two(input: &Vec<&str>) -> u32 {
    // We can't use standard bit counts here as we need to discard numbers that are no longer relevant
    // We have to check bit positions for each possible value I think.
    // There will be different candidates for oxy and co2 values, so we can't do this  nicely in one loop
    // Also we won't know what numbers to discard until we have traversed all candidates
    // Could build a map, with 0 and 1 being the keys and the values being the candidates. Then we should be able to efficiently get the candidates
    let unset_bit: &u8 = "0".as_bytes().first().unwrap();
    let set_bit: &u8 = "1".as_bytes().first().unwrap();

    let mut oxy_gen_candidates = input.clone()
    let mut oxy_gen_candidates = (0..input.len()).collect::<Vec<usize>>();
    let mut co2_scrubber_candidates = (0..input.len()).collect::<Vec<usize>>();
    let mut cur_bit = 0;
    while oxy_gen_candidates.len() > 1 {
        let mut bit_map = HashMap::new();
        for i in &oxy_gen_candidates {
            let mut bit = input[i].as_bytes()[cur_bit];
            bit_map.entry(bit).or_insert(Vec::new()).push(*i);
        }
        println!("Len of candidates = {}", &oxy_gen_candidates.len());

        let unset_bits = bit_map.get(unset_bit);
        let set_bits = bit_map.get(set_bit);
        if unset_bits.is_none() {
            oxy_gen_candidates = bit_map[set_bit].clone();
        } else if set_bits.is_none() {
            oxy_gen_candidates = bit_map[unset_bit].clone();
        } else if set_bits.unwrap().len() >= unset_bits.unwrap().len() {
            println!(
                "Setting new candidate of set bits= {}",
                oxy_gen_candidates.len()
            );
            oxy_gen_candidates = bit_map[set_bit].clone();
        } else {
            println!(
                "Setting new candidate of unset bits = {}",
                oxy_gen_candidates.len()
            );
            oxy_gen_candidates = bit_map[unset_bit].clone();
        }
        println!("Len of candidates = {}", oxy_gen_candidates.len());
        break;
    }
    println!("{}", input[*oxy_gen_candidates.first().unwrap()]);

    0
}

fn binary_to_int(binary: Vec<bool>) -> i32 {
    // let mut multiplier = 1;
    let mut result = 0;
    for i in (0..binary.len()).rev() {
        if binary[i] {
            result += 1;
            result << 1;
            // result += multiplier;
            // multiplier *= 2;
        }
    }
    return result;
}

pub fn main() {
    // let input = read_input(include_str!("../../input/day03.txt"));
    // let part1 = part_one(&input);
    // let part2 = part_two(&input);
    // println!("[Day 03] Part1: {}\tPart2: {}", part1, part2);
}

mod tests {
    use days::day03::{binary_to_int, part_one, part_two, read_input};

    #[test]
    fn examples() {
        let result = binary_to_int(Vec::from([true, false, true]));
        println!("{}", result);
        let input = read_input(include_str!("../../input/day03_example.txt"));
        // let part_one_res = part_one(&input);
        // assert_eq!(part_one_res, 198);
        // let part_two_res = part_two(&input);
        // assert_eq!(part_two_res, 230);
    }
}
