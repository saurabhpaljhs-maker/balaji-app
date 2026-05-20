// Tier 2: API Layer — Express REST backend
const express = require('express');
const cors = require('cors');
const path = require('path');
const { mouldingCatalogue, frameProducts } = require('./data');

const app = express();
const PORT = process.env.PORT || 3001;

app.use(cors());
app.use(express.json());

// Serve frontend static files
app.use(express.static(path.join(__dirname, '../frontend/public')));

// ─── API ROUTES ────────────────────────────────────────────────────────────────

// GET all mouldings (with optional filter)
app.get('/api/mouldings', (req, res) => {
  const { size, search } = req.query;
  let results = mouldingCatalogue;

  if (size) {
    results = results.filter(m => m.size === size);
  }
  if (search) {
    const q = search.toLowerCase();
    results = results.filter(m =>
      m.colors.some(c => c.toLowerCase().includes(q)) ||
      m.modelNo.includes(q) || m.size.includes(q)
    );
  }
  res.json({ count: results.length, data: results });
});

// GET unique sizes for filter
app.get('/api/mouldings/sizes', (req, res) => {
  const sizes = [...new Set(mouldingCatalogue.map(m => m.size))].sort((a,b) =>
    parseFloat(a) - parseFloat(b)
  );
  res.json(sizes);
});

// GET single moulding by model
app.get('/api/mouldings/:model', (req, res) => {
  const found = mouldingCatalogue.find(m => m.modelNo === req.params.model);
  if (!found) return res.status(404).json({ error: 'Model not found' });
  res.json(found);
});

// GET frame products + pricing
app.get('/api/frames', (req, res) => {
  res.json({ count: frameProducts.length, data: frameProducts });
});

// POST quote calculator
app.post('/api/quote', (req, res) => {
  const { frameSize, moulding, quantity = 1 } = req.body;
  const frame = frameProducts.find(f => f.size === frameSize);
  const mouldingData = mouldingCatalogue.find(m => m.modelNo === moulding);

  if (!frame) return res.status(400).json({ error: 'Invalid frame size' });

  const basePrice = frame.price;
  const widthMultiplier = mouldingData ? 1 + (mouldingData.width / 100) : 1.2;
  const total = Math.round(basePrice * widthMultiplier * quantity);

  res.json({
    frameSize, moulding, quantity,
    unitPrice: Math.round(basePrice * widthMultiplier),
    total,
    breakdown: {
      baseFramePrice: basePrice,
      mouldingPremium: Math.round(basePrice * (widthMultiplier - 1)),
      quantity
    }
  });
});

// GET business info
app.get('/api/info', (req, res) => {
  res.json({
    name: "BALAJI Photo Frames",
    tagline: "Agra's Finest Frame Studio",
    established: 2000,
    location: "Agra, Uttar Pradesh",
    whatsapp: "+91-9999999999",
    speciality: "Premium custom photo frames — handcrafted with finest beading & finishing",
    supplier: "Accurate Industries (ADS Art Poster Group)"
  });
});

// Fallback: serve frontend
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, '../frontend/public/index.html'));
});

app.listen(PORT, () => {
  console.log(`✅ BALAJI API running at http://localhost:${PORT}`);
  console.log(`   Mouldings: ${mouldingCatalogue.length} models loaded`);
  console.log(`   Frame sizes: ${frameProducts.length} products`);
});
