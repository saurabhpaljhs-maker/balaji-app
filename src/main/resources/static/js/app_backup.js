/* ═══════════════════════════════════════════════════════════
   BALAJI PHOTO FRAMES — app.js (FIXED - Dynamic Moulding Loading)
   ═══════════════════════════════════════════════════════════ */

/* ── State ─────────────────────────────────── */
const state = {
    sizeId:       null,
    beadingId:    null,
    coverId:      null,
    photoPath:    null,
    photoDataUrl: null,
};

/* ── Init ──────────────────────────────────── */
document.addEventListener('DOMContentLoaded', () => {
    // CRITICAL: Load beadings from API first
    loadBeadingsFromAPI();
    loadCoversFromAPI();
    
    initUploadZone();
    initPayTabs();

    const firstSize    = document.querySelector('.size-btn');
    const firstBeading = document.querySelector('.bead-card');
    const firstCover   = document.querySelector('.cover-card');
    if (firstSize)    selectSize(firstSize);
    if (firstBeading) selectBeading(firstBeading);
    if (firstCover)   selectCover(firstCover);
});

/* ── DYNAMIC LOAD: Beadings from API ─────────────────────────── */
function loadBeadingsFromAPI() {
    const grid = document.querySelector('.beading-grid');
    if (!grid) {
        console.warn('beading-grid not found');
        return;
    }

    // Clear existing items (from Thymeleaf fallback)
    grid.innerHTML = '';

    fetch('/api/frame/beadings')
        .then(r => {
            if (!r.ok) throw new Error(`HTTP ${r.status}`);
            return r.json();
        })
        .then(beadings => {
            console.log('Loaded beadings:', beadings.length);
            
            if (!beadings || beadings.length === 0) {
                grid.innerHTML = '<p style="grid-column:1/-1;color:#888;text-align:center">No moulding options available</p>';
                return;
            }

            beadings.forEach(b => {
                const card = document.createElement('div');
                card.className = 'bead-card';
                card.dataset.id = b.id;
                card.dataset.border = b.borderPx || 10;
                card.dataset.gradient = b.gradientCss || 'linear-gradient(135deg,#C9A84C,#8B6914)';
                card.onclick = function() { selectBeading(this); };

                card.innerHTML = `
                    <div class="bead-img" style="background:${b.gradientCss || 'linear-gradient(135deg,#C9A84C,#8B6914)'};width:100%;height:80px;border-radius:6px;display:flex;align-items:center;justify-content:center;color:#fff;font-size:12px;font-weight:bold;text-align:center;padding:4px;">
                        ${b.pattern || 'Design'}
                    </div>
                    <div class="bead-width">${b.displayWidth || b.widthLabel || '1"'}</div>
                    <div class="bead-add">+₹${b.additionalPrice || 0}</div>
                    <div class="bead-desc">${b.description || 'Moulding'}</div>
                `;

                grid.appendChild(card);
            });

            // Auto-select first beading
            const firstBead = document.querySelector('.bead-card');
            if (firstBead) selectBeading(firstBead);
        })
        .catch(err => {
            console.error('Failed to load beadings:', err);
            grid.innerHTML = '<p style="grid-column:1/-1;color:#d00;text-align:center">Failed to load moulding options</p>';
        });
}

/* ── DYNAMIC LOAD: Covers from API ─────────────────────────── */
function loadCoversFromAPI() {
    const grid = document.querySelector('.cover-grid');
    if (!grid) {
        console.warn('cover-grid not found');
        return;
    }

    // Clear existing items
    grid.innerHTML = '';

    fetch('/api/frame/covers')
        .then(r => {
            if (!r.ok) throw new Error(`HTTP ${r.status}`);
            return r.json();
        })
        .then(covers => {
            console.log('Loaded covers:', covers.length);
            
            if (!covers || covers.length === 0) {
                grid.innerHTML = '<p style="grid-column:1/-1;color:#888;text-align:center">No cover options available</p>';
                return;
            }

            covers.forEach(c => {
                const card = document.createElement('div');
                card.className = 'cover-card';
                card.dataset.id = c.id;
                card.dataset.type = c.coverType;
                card.onclick = function() { selectCover(this); };

                card.innerHTML = `
                    <div class="cover-emoji">${c.emoji || c.displayName?.charAt(0) || '◯'}</div>
                    <div class="cover-name">${c.displayName || c.coverType}</div>
                    <div class="cover-price">+₹${c.additionalPrice || 0}</div>
                    <div class="cover-desc">${c.description || ''}</div>
                `;

                grid.appendChild(card);
            });

            // Auto-select first cover
            const firstCover = document.querySelector('.cover-card');
            if (firstCover) selectCover(firstCover);
        })
        .catch(err => {
            console.error('Failed to load covers:', err);
            grid.innerHTML = '<p style="grid-column:1/-1;color:#d00;text-align:center">Failed to load cover options</p>';
        });
}

/* ── Upload Zone ───────────────────────────── */
function initUploadZone() {
    const uploadZone = document.getElementById('uploadZone');
    const fileInput  = document.getElementById('fileInput');
    if (!uploadZone || !fileInput) return;

    uploadZone.addEventListener('click', () => fileInput.click());
    uploadZone.addEventListener('dragover', e => {
        e.preventDefault();
        uploadZone.classList.add('dragging');
    });
    uploadZone.addEventListener('dragleave', () => {
        uploadZone.classList.remove('dragging');
    });
    uploadZone.addEventListener('drop', e => {
        e.preventDefault();
        uploadZone.classList.remove('dragging');
        const file = e.dataTransfer.files[0];
        if (file && file.type.startsWith('image/')) processFile(file);
    });
    fileInput.addEventListener('change', () => {
        if (fileInput.files[0]) processFile(fileInput.files[0]);
    });
}

function processFile(file) {
    const reader = new FileReader();
    reader.onload = e => {
        state.photoDataUrl = e.target.result;

        // Show in upload zone
        const photoPreview = document.getElementById('photoPreview');
        const uploadZone   = document.getElementById('uploadZone');
        const uploadIcon   = uploadZone?.querySelector('.upload-icon');
        const uploadText   = uploadZone?.querySelector('.upload-text');

        if (photoPreview) {
            photoPreview.src = e.target.result;
            photoPreview.style.display = 'block';
        }
        if (uploadIcon) uploadIcon.style.display = 'none';
        if (uploadText) uploadText.style.display = 'none';
        if (uploadZone) uploadZone.classList.add('has-photo');

        const frameImg     = document.getElementById('framePhotoImg');
        const frameNoPhoto = document.getElementById('frameNoPhoto');

        if (frameImg) {
            frameImg.src = e.target.result;
            frameImg.style.display = 'block';
        }
        if (frameNoPhoto) frameNoPhoto.style.display = 'none';

        uploadToServer(file);
    };
    reader.readAsDataURL(file);
}

function uploadToServer(file) {
    const fd = new FormData();
    fd.append('file', file);
    fetch('/api/frame/upload', { method: 'POST', body: fd })
        .then(r => r.json())
        .then(data => { if (data.path) state.photoPath = data.path; })
        .catch(() => {});
}

/* ── Size Selection ────────────────────────── */
function selectSize(el) {
    document.querySelectorAll('.size-btn').forEach(b => b.classList.remove('active'));
    el.classList.add('active');
    state.sizeId = el.dataset.id;
    fetchPrice();
    const activeBead = document.querySelector('.bead-card.active');
    if (activeBead) updateFramePreview(activeBead.dataset.border, activeBead.dataset.gradient);
}

/* ── Beading Selection ─────────────────────── */
function selectBeading(el) {
    document.querySelectorAll('.bead-card').forEach(b => b.classList.remove('active'));
    el.classList.add('active');
    state.beadingId = el.dataset.id;

    updateFramePreview(el.dataset.border, el.dataset.gradient);
    fetchPrice();
}

/* ── Cover Selection ───────────────────────── */
function selectCover(el) {
    document.querySelectorAll('.cover-card').forEach(b => b.classList.remove('active'));
    el.classList.add('active');
    state.coverId  = el.dataset.id;

    const glassLayer = document.getElementById('glassLayer');
    const coverType  = el.dataset.type;
    if (glassLayer) {
        glassLayer.style.display =
            (coverType === 'GLASS' || coverType === 'ACRYLIC') ? 'block' : 'none';
    }

    fetchPrice();
}

/* ── Frame Preview ─────────────────────────── */
function updateFramePreview(borderPx, gradient) {
    const sizeBtn = document.querySelector('.size-btn.active');
    if (!sizeBtn) return;

    const w      = parseInt(sizeBtn.dataset.w || 8);
    const h      = parseInt(sizeBtn.dataset.h || 10);
    const maxDim = 200;
    const ratio  = w / h;

    let pw, ph;
    if (ratio >= 1) { pw = maxDim; ph = Math.round(maxDim / ratio); }
    else            { ph = maxDim; pw = Math.round(maxDim * ratio); }

    const bp = parseInt(borderPx || 10);

    const frameOuter  = document.getElementById('frameOuter');
    const frameBorder = document.getElementById('frameBorder');
    const framePhoto  = document.getElementById('framePhotoBox');

    if (!frameOuter) return;

    frameOuter.style.width  = (pw + bp * 2) + 'px';
    frameOuter.style.height = (ph + bp * 2) + 'px';

    if (frameBorder && gradient) {
        frameBorder.style.background = gradient;
    }

    if (framePhoto) {
        framePhoto.style.width  = pw + 'px';
        framePhoto.style.height = ph + 'px';
        framePhoto.style.inset  = bp + 'px';
    }
}

/* ── Price Fetch ───────────────────────────── */
function fetchPrice() {
    if (!state.sizeId || !state.beadingId || !state.coverId) return;

    fetch(`/api/frame/price?sizeId=${state.sizeId}&beadingId=${state.beadingId}&coverId=${state.coverId}`)
        .then(r => r.json())
        .then(data => {
            const prSize    = document.getElementById('prSize');
            const prBeading = document.getElementById('prBeading');
            const prCover   = document.getElementById('prCover');
            const prTotal   = document.getElementById('prTotal');
            const payBtn    = document.getElementById('payTotalBtn');
            const codBtn    = document.getElementById('codTotalBtn');

            if (prSize)    prSize.textContent    = `${data.sizeLabel} — ₹${fmt(data.basePrice)}`;
            if (prBeading) prBeading.textContent = `₹${fmt(data.beadingPrice)}`;
            if (prCover)   prCover.textContent   = `₹${fmt(data.coverPrice)}`;
            if (prTotal)   prTotal.textContent   = `₹ ${fmt(data.totalPrice)}`;
            if (payBtn)    payBtn.textContent    = `₹${fmt(data.totalPrice)}`;
            if (codBtn)    codBtn.textContent    = `₹${fmt(data.totalPrice)}`;

            const activeBead = document.querySelector('.bead-card.active');
            if (activeBead) {
                updateFramePreview(activeBead.dataset.border, activeBead.dataset.gradient);
            }
        })
        .catch(() => {});
}

function fmt(num) {
    return parseFloat(num).toLocaleString('en-IN', {
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
    });
}

/* ── Payment Modal ─────────────────────────── */
function openPayment() {
    if (!state.sizeId || !state.beadingId || !state.coverId) {
        showToast('Please select size, beading and cover first!', true);
        return;
    }
    const payFormStep = document.getElementById('payFormStep');
    const successStep = document.getElementById('successStep');
    const backdrop    = document.getElementById('modalBackdrop');

    if (payFormStep) payFormStep.style.display = 'block';
    if (successStep) successStep.style.display = 'none';
    if (backdrop)    backdrop.classList.add('open');
    document.body.style.overflow = 'hidden';
}

function closePayment() {
    const backdrop = document.getElementById('modalBackdrop');
    if (backdrop) backdrop.classList.remove('open');
    document.body.style.overflow = '';
}

document.addEventListener('DOMContentLoaded', () => {
    const backdrop = document.getElementById('modalBackdrop');
    if (backdrop) {
        backdrop.addEventListener('click', e => {
            if (e.target === backdrop) closePayment();
        });
    }
});

/* ── Pay Tabs ──────────────────────────────── */
function initPayTabs() {
    document.querySelectorAll('.pay-tab').forEach(tab => {
        tab.addEventListener('click', () => {
            const target = tab.dataset.tab;
            document.querySelectorAll('.pay-tab').forEach(t => t.classList.remove('active'));
            document.querySelectorAll('.pay-pane').forEach(p => p.classList.remove('active'));
            tab.classList.add('active');
            const pane = document.getElementById('pane-' + target);
            if (pane) pane.classList.add('active');

            const rzpBtn = document.getElementById('rzpPayBtn');
            const codBtn = document.getElementById('codConfirmBtn');
            if (rzpBtn && codBtn) {
                if (target === 'cash') {
                    rzpBtn.style.display = 'none';
                    codBtn.style.display = 'block';
                } else {
                    rzpBtn.style.display = 'block';
                    codBtn.style.display = 'none';
                }
            }
        });
    });
}

/* ── Card Formatting ───────────────────────── */
function formatCard(input) {
    let v = input.value.replace(/\D/g, '').substring(0, 16);
    input.value = v.replace(/(.{4})/g, '$1 ').trim();
}

/* ── COD Order Submit ──────────────────────── */
function submitOrder() {
    const name  = document.getElementById('cName')?.value.trim();
    const phone = document.getElementById('cPhone')?.value.trim();

    if (!name)  { showToast('Please enter your name', true); return; }
    if (!phone || phone.replace(/\D/g, '').length < 10) {
        showToast('Please enter a valid phone number', true); return;
    }

    const payload = {
        customerName:      name,
        customerPhone:     phone.replace(/\D/g, ''),
        customerEmail:     document.getElementById('cEmail')?.value.trim() || '',
        deliveryAddress:   document.getElementById('cAddress')?.value.trim() || '',
        frameSizeId:       parseInt(state.sizeId),
        beadingOptionId:   parseInt(state.beadingId),
        coverOptionId:     parseInt(state.coverId),
        paymentMethod:     'CASH',
        uploadedPhotoPath: state.photoPath,
    };

    const btn = document.getElementById('codConfirmBtn');
    if (btn) { btn.textContent = 'Placing order...'; btn.disabled = true; }

    fetch('/api/order/place', {
        method:  'POST',
        headers: { 'Content-Type': 'application/json' },
        body:    JSON.stringify(payload),
    })
    .then(r => r.json())
    .then(data => {
        if (data.error) {
            showToast(data.error, true);
            if (btn) { btn.textContent = '✓ Confirm COD Order'; btn.disabled = false; }
            return;
        }
        showSuccess(data, name, phone);
    })
    .catch(() => {
        showToast('Network error. Please try again.', true);
        if (btn) { btn.textContent = '✓ Confirm COD Order'; btn.disabled = false; }
    });
}

/* ── Show Success ──────────────────────────── */
function showSuccess(data, name, phone) {
    const payFormStep = document.getElementById('payFormStep');
    const successStep = document.getElementById('successStep');
    if (payFormStep) payFormStep.style.display = 'none';
    if (successStep) successStep.style.display  = 'block';

    const elName  = document.getElementById('successName');
    const elPhone = document.getElementById('successPhone');
    const elId    = document.getElementById('successOrderId');
    const elTotal = document.getElementById('successTotal');
    const waLink  = document.getElementById('waLink');

    if (elName)  elName.textContent  = name;
    if (elPhone) elPhone.textContent = phone;
    if (elId)    elId.textContent    = '#' + data.orderId;
    if (elTotal) elTotal.textContent = '₹' + fmt(data.totalPrice);
    if (waLink)  waLink.href =
        `https://wa.me/918299576949?text=Hello%20BALAJI%20Frames!%20Order%20%23${data.orderId}%20placed.%20Name:%20${encodeURIComponent(name)}`;
}

/* ── Toast ─────────────────────────────────── */
function showToast(msg, isError = false) {
    const t = document.getElementById('toast');
    if (!t) return;
    t.textContent = msg;
    t.className   = 'toast' + (isError ? ' toast-error' : '');
    t.classList.add('show');
    setTimeout(() => t.classList.remove('show'), 3500);
}
