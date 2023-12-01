#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <ostream>
#include <set>
#include <stdint.h>
#include <string>
#include <vector>

using namespace std;

// Implement cout for vectors
template <typename S>
ostream &operator<<(ostream &os, const vector<S> &vector) {
  os << "[";
  for (int i = 0; i < vector.size(); i++) {
    auto element = vector[i];
    os << element;
    if (i != vector.size() - 1) {
      os << ", ";
    }
  }
  os << "]";
  return os;
}

void part1(vector<string> input) {
  int result = 0;
  for (string s : input) {
    int first = -1, last = -1;
    for (char c : s) {
      if ('0' <= c && c <= '9') {
        if (first == -1) {
          first = c - '0';
        }
        last = c - '0';
      }
    }
    int num = first * 10 + last;
    result += num;
  }
  cout << "Part 1: " << result << endl;
}

bool substring_starts_at_index(string s, string substring, int index) {
  bool result = true;
  for (int i = 0; i < substring.length(); i++) {
    bool s_ended = i >= s.length();
    bool chars_equal = s[index + i] == substring[i];
    if (s_ended || !chars_equal) {
      result = false;
    }
  }
  return result;
}

void part2(vector<string> input) {
  vector<string> digits{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
  vector<string> digit_words{"one", "two",   "three", "four", "five",
                             "six", "seven", "eight", "nine"};
  int result = 0;
  for (string s : input) {
    int first = -1, last = -1;
    for (int i = 0; i < s.length(); i++) {
      for (int digit = 1; digit <= digits.size(); digit++) {
        bool digit_starts = substring_starts_at_index(s, digits[digit - 1], i);
        bool digit_word_starts =
            substring_starts_at_index(s, digit_words[digit - 1], i);
        if (digit_starts || digit_word_starts) {
          if (first == -1) {
            first = digit;
          }
          last = digit;
        }
      }
    }
    int num = 10 * first + last;
    result += num;
  }
  cout << "Part 2: " << result << endl;
}

vector<string> read_input() {
  ifstream input_file;
  input_file.open("input.txt");
  vector<string> input;
  while (input_file.good()) {
    string line;
    getline(input_file, line);
    input.push_back(line);
  }
  return input;
}

int main() {
  ios_base::sync_with_stdio(0);
  vector<string> input = read_input();
  // vectors are pass by value, no copying needed
  part1(input);
  part2(input);
  return 0;
}
