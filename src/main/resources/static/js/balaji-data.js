// Tier 1: Data Layer — Moulding catalogue from Accurate Industries PDF
const mouldingCatalogue = [
  {
    modelNo: "50", size: "0.7\"", width: 15, height: 18,
    colors: ["White Gold", "Coffee Ivory", "Sky Blue Gold", "N Wood Pin", "White Black"]
  },
  {
    modelNo: "5", size: "1\"", width: 24, height: 13,
    colors: ["Black T-2", "Black T-3", "CZ (RST)", "Black T-4", "Black (RST)", "Black Gold (RST)",
             "Black Radient Orange T-4", "Black Radient Red T-4", "Black Tiger T-4"]
  },
  {
    modelNo: "221", size: "1\"", width: 21, height: 20,
    colors: ["N Wood Pin", "White Gold", "Sky Blue Gold", "Black (RST)", "White (RST)",
             "Black Silver (RST)", "Black Gold (RST)"]
  },
  {
    modelNo: "23", size: "1\"", width: 24, height: 19,
    colors: ["CZ (RST)", "Rose White (RST)", "Gold White (RST)", "Pine Wood (RST)",
             "I Brown Pine W (RST)", "Black (RST)", "Coffee Ivory (RST)", "Black Brown (RST)",
             "Black Pine wood (RST)", "Gold Print Maroon (RST)", "White (RST)"]
  },
  {
    modelNo: "149", size: "1\"", width: 26, height: 15,
    colors: ["White Rust", "Black Rust", "Radient Orange"]
  },
  {
    modelNo: "72", size: "1\"", width: 27, height: 16,
    colors: ["CZ (RST)"]
  },
  {
    modelNo: "6", size: "1.2\"", width: 30, height: 12,
    colors: ["CZ", "Black Gold"]
  },
  {
    modelNo: "122", size: "1.25\"", width: 26, height: 21,
    colors: ["Natural Louvers Gold", "CZ (RST)", "Night Wood Gold", "White Rose (RST)",
             "White Gold (RST)", "White Black (RST)", "Black Gold (RST)", "White Marron (RST)",
             "White Pine Wood (RST)", "Pine Wood Brown", "Black (RST)"]
  },
  {
    modelNo: "32", size: "1.25\"", width: 32, height: 27,
    colors: ["B Gold Louvers", "B Pinewood", "B Louvers", "B Gold Pine Wood",
             "Gold Black T-1", "Black Gold Tiger T-5", "CZ T-1", "B Gold Tiger T-1"]
  },
  {
    modelNo: "33", size: "1.25\"", width: 33, height: 22,
    colors: ["Natural Louvers", "Pin wood Black", "Black Gold", "Full Black",
             "Royal Brown Print", "Maroon Gold", "Maroon Gold T-6"]
  },
  {
    modelNo: "51", size: "1.35\"", width: 35, height: 12,
    colors: ["Radiant Red T-5", "Radiant Orange T-5", "Copper Tiger T-5",
             "Gold Black T-5", "CZ T-5", "Black T-5"]
  },
  {
    modelNo: "123", size: "1.5\"", width: 40, height: 16,
    colors: ["Rose White (RST)", "Gold White (RST)", "Black Pin wood (RST)",
             "Black Radiant Orange P", "Black Plain", "Black Radiant Red P",
             "Black Brown", "CZ Plain", "Coffee Ivory", "Black (RST)"]
  },
  {
    modelNo: "321", size: "1.5\"", width: 40, height: 22,
    colors: ["Black G", "Natural Louvers B", "Pine wood B"]
  },
  {
    modelNo: "15", size: "1.75\"", width: 45, height: 13,
    colors: ["Black Pine wood T-5", "Black Brown T-5", "Black T-5", "CZ-T5"]
  },
  {
    modelNo: "168", size: "2\"", width: 45, height: 26,
    colors: ["Black (RST)", "Black Gold (RST)", "Black Louvers -B", "Maroon G (RST)",
             "CZ GOLD", "Maroon Gold - M", "Rose White (RST)", "CZ (RST)", "Louvers -B"]
  },
  {
    modelNo: "105", size: "2\"", width: 52, height: 24,
    colors: ["Maroon G (RST)", "Black (RST)", "CZ (RST)", "Pine Wood Black (RST)",
             "Louvers Black (RST)", "Pine Wood B (RST)"]
  },
  {
    modelNo: "159", size: "2\"", width: 50, height: 14,
    colors: ["Maroon T-6", "Black T-6", "Black T-7", "Black T-8", "Black T-9",
             "Black T-10", "Black T-11", "Black T-12"]
  },
  {
    modelNo: "312", size: "2\"", width: 51, height: 32,
    colors: ["CZ (RST)", "Black Dark Wood (RST)", "Black Gold (RST)"]
  },
  {
    modelNo: "212", size: "3\"", width: 75, height: 29,
    colors: ["Night wood-B", "CZ-RST", "Royal Maroon sign", "Black (RST)"]
  }
];

const frameProducts = [
  { size: "4x6",   label: "Wallet / ID Photo",    price: 80  },
  { size: "5x7",   label: "Passport / Portrait",  price: 100 },
  { size: "6x8",   label: "Table Display",         price: 130 },
  { size: "8x10",  label: "Photo Portrait",        price: 180 },
  { size: "10x12", label: "Family Photo",          price: 240 },
  { size: "10x14", label: "Event Photo",           price: 280 },
  { size: "10x15", label: "Panorama",              price: 320 },
  { size: "12x18", label: "Large Portrait",        price: 420 },
  { size: "16x20", label: "Gallery Print",         price: 580 },
  { size: "20x24", label: "Premium Wall Art",      price: 780 },
];

module.exports = { mouldingCatalogue, frameProducts };
