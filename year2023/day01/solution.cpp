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

/*
 * function to find the start indices of each occurrence of sub_s in s
 */
vector<int> substring(string s, string sub_s) {
  vector<int> indices;
  for (int i = 0; i < s.length(); i++) {
    for (int j = 0; j < sub_s.length() && i + j < s.length(); j++) {
      if (s[i + j] != sub_s[j]) {
        break;
      }
      if (j == sub_s.length() - 1) {
        indices.push_back(i);
      }
    }
  }
  return indices;
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

void part2(vector<string> input) {
  int result = 0;
  for (string s : input) {
    int32_t first_index = INT32_MAX, last_index = INT32_MIN;
    int first = -1, last = -1;
    vector<string> digit_words = {"one", "two",   "three", "four", "five",
                                  "six", "seven", "eight", "nine"};
    vector<string> digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    for (int i = 0; i < digits.size(); i++) {
      vector<int> digit_word_indices = substring(s, digit_words[i]);
      vector<int> digit_indices = substring(s, digits[i]);
      int minimum, maximum;
      if (digit_word_indices.empty() && digit_indices.empty()) {
        continue;
      } else if (digit_word_indices.empty()) {
        minimum = digit_indices[0];
        maximum = digit_indices[digit_indices.size() - 1];
      } else if (digit_indices.empty()) {
        minimum = digit_word_indices[0];
        maximum = digit_word_indices[digit_word_indices.size() - 1];
      } else {
        minimum = min(digit_word_indices[0], digit_indices[0]);
        maximum = max(digit_word_indices[digit_word_indices.size() - 1],
                      digit_indices[digit_indices.size() - 1]);
      }
      if (minimum < first_index) {
        first = i + 1;
      }
      first_index = min(minimum, first_index);
      if (maximum > last_index) {
        last = i + 1;
      }
      last_index = max(maximum, last_index);
    }
    int num = first * 10 + last;
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
